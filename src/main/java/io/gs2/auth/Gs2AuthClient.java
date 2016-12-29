/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.auth;

import org.apache.http.client.methods.HttpPost;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.auth.control.CreateOnceOnetimeTokenRequest;
import io.gs2.auth.control.CreateOnceOnetimeTokenResult;
import io.gs2.auth.control.CreateTimeOnetimeTokenRequest;
import io.gs2.auth.control.CreateTimeOnetimeTokenResult;
import io.gs2.auth.control.LoginRequest;
import io.gs2.auth.control.LoginResult;
import io.gs2.auth.control.LoginWithSignRequest;
import io.gs2.auth.control.LoginWithSignResult;
import io.gs2.exception.BadRequestException;
import io.gs2.exception.InternalServerErrorException;
import io.gs2.exception.UnauthorizedException;
import io.gs2.model.IGs2Credential;

/**
 * GS2 Auth APIクライアント。
 * 
 * @author Game Server Services, Inc.
 *
 */
public class Gs2AuthClient extends AbstractGs2Client<Gs2AuthClient> {
	
	public static String ENDPOINT = "auth";
	
	/**
	 * コンストラクタ。
	 * 
	 * @param credential 認証情報
	 */
	public Gs2AuthClient(IGs2Credential credential) {
		super(credential);
	}
	
	/**
	 * ログイン。
	 * 
	 * GS2のサービスを利用するにあたってユーザの認証に必要となるアクセストークンを発行します。<br>
	 * アクセストークンの発行には以下の情報が必要となります。<br>
	 * <ul>
	 * <li>ユーザID</li>
	 * <li>サービスID</li>
	 * </ul>
	 * ユーザID には ログインするユーザのIDを指定してください。<br>
	 * GS2 はアカウント管理機能を持ちませんので、ユーザID は別途アカウント管理システムで発行したIDを指定する必要があります。<br>
	 * アカウントIDの文字種などには制限はありません。<br>
	 * <br>
	 * サービスID には任意の文字列を指定できます。<br>
	 * ここで指定したサービスIDにもとづいて、その後アクセストークンで利用できるGSIを制限するのに利用します。<br>
	 * <br>
	 * サービスの制限は GSI(AWSのIAMのようなもの) のセキュリティポリシーで設定することができます。<br>
	 * 例えば、GSIに設定されたセキュリティポリシーが service-0001 によるアクセスを許可する。という設定の場合、<br>
	 * service-0002 というサービスIDで発行されたアクセストークンとGSIの組み合わせでリクエストを出してもリジェクトされるようになります。<br>
	 * <br>
	 * これによって、下記のようなアクセスコントロールを同一アカウント内で実現できます。<br>
	 * <ul>
	 * <li>GSI(A) 許可するアクション(GS2Inbox:*) 許可するサービス(service-0001)</li>
	 * <li>GSI(B) 許可するアクション(GS2Stamina:*) 許可するサービス(service-0002)</li>
	 * </ul>
	 * この場合、service-0001 向けに発行されたアクセストークンと GSI(B) の組み合わせではサービスを利用することはできません。<br>
	 * そのため、service-0001 向けのアクセストークンでは GS2-Stamina を利用することはできないことになります。<br>
	 * 
	 * @param request リクエストパラメータ
	 * @return ログイン結果
	 * 
	 * @throws BadRequestException リクエストパラーメータに誤りがある場合にスローされます
	 * @throws UnauthorizedException 認証に失敗した場合にスローされます
	 * @throws InternalServerErrorException 未知のサーバエラーが発生した場合にスローされます
	 */
	public LoginResult login(LoginRequest request) throws BadRequestException, UnauthorizedException, InternalServerErrorException {
		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceId", request.getServiceId())
				.put("userId", request.getUserId());
		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/login", 
				credential, 
				ENDPOINT,
				LoginRequest.Constant.MODULE, 
				LoginRequest.Constant.FUNCTION,
				body.toString());
		return doRequest(post, LoginResult.class);
	}

	/**
	 * GS2-Account 認証署名検証付きログイン。
	 * 
	 * Login の機能に更に GS2-Account による認証署名検証機能を追加したAPIです。<br>
	 * 通常の Login はクライアントから直接利用するには脆弱で、任意のユーザIDでログインできてしまいます。<br>
	 * <br>
	 * それに対して、こちらのAPIは GS2-Account の認証情報による署名検証が実装されており、<br>
	 * 他人になりすましてログインすることができなくなっています。<br>
	 * <br>
	 * 独自のアカウントシステムを利用してサーバ経由でGS2にログインする場合は通常のLoginを<br>
	 * GS2-Accountを利用してクライアントから直接GS2にログインする場合は本APIを利用することでセキュアにGS2を利用することが出来ます。<br>
	 * 
	 * @param request リクエストパラメータ
	 * @return ログイン結果
	 * 
	 * @throws BadRequestException リクエストパラーメータに誤りがある場合にスローされます
	 * @throws UnauthorizedException 認証に失敗した場合にスローされます
	 * @throws InternalServerErrorException 未知のサーバエラーが発生した場合にスローされます
	 */
	public LoginWithSignResult loginWithSign(LoginWithSignRequest request) throws BadRequestException, UnauthorizedException, InternalServerErrorException {
		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceId", request.getServiceId())
				.put("userId", request.getUserId())
				.put("keyName", request.getKeyName())
				.put("sign", request.getSign());
		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/login/signed", 
				credential, 
				ENDPOINT,
				LoginWithSignRequest.Constant.MODULE, 
				LoginWithSignRequest.Constant.FUNCTION,
				body.toString());
		return doRequest(post, LoginWithSignResult.class);
	}
	
	/**
	 * 時間制限付きのワンタイムトークンを発行します。
	 * 
	 * 時間制限付きのワンタイムトークンはGSIの代わりとなるトークンであり、署名の計算が不要となります。<br>
	 * ゲームプログラムから直接GS2のサービスを利用するにあたって、<br>
	 * GSIのシークレットキーをプログラムに埋め込みたくない場合に信頼できるサーバでワンタイムトークンを発行し、<br>
	 * ワンタイムトークンを利用してGS2のサービスを利用することが出来ます。<br>
	 * <br>
	 * ワンタイムトークンの発行には条件があり、この関数の引数として指定する GS2-Script が返す GS2-Identifier のユーザID相当の振る舞いしか出来ません。<br>
	 * これによって、クライアントの不正アクセスによって、より強い権限のユーザを奪取されることを防ぐことが出来ます。<br>
	 * <br>
	 * 逆に言えば、ワンタイムトークンで利用する GS2-Identifier のユーザの権限はゲームに必要な権限に限定するべきです。<br>
	 * 例えば、ゲームプレイヤーにとって新しい受信ボックスの作成やスタミナプールの作成というアクションは不要なはずです。<br>
	 * そのため、これらのアクションが実行できないようポリシーを設定したユーザ権限を応答するべきです。<br>
	 * 
	 * @param request リクエストパラメータ
	 * @return ワンタイムトークン発行結果
	 */
	public CreateTimeOnetimeTokenResult createTimeOntimeToken(CreateTimeOnetimeTokenRequest request) {
		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("scriptName", request.getScriptName());
		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/onetime/time/token", 
				credential, 
				ENDPOINT,
				CreateTimeOnetimeTokenRequest.Constant.MODULE, 
				CreateTimeOnetimeTokenRequest.Constant.FUNCTION,
				body.toString());
		return doRequest(post, CreateTimeOnetimeTokenResult.class);
	}
	
	/**
	 * 有効回数付きのワンタイムトークンを発行します。
	 * 
	 * 特定の用途に1回だけ利用できるワンタイムトークンを発行します。<br>
	 * ゲーム内のアクションには、受信ボックスのメッセージ取得や現在のスタミナ値の取得など、特に制限なく呼び出されて問題のないアクションだけでなく<br>
	 * スタミナ値の回復や、ゲーム内マネーの付与。アイテムの付与といった、繰り返し実行されたくないアクションが存在します。<br>
	 * このワンタイムトークンはそのようなアクションをサポートするための機能で、発行後1度だけ許可されたアクションを実行できます。<br>
	 * <br>
	 * アクションを許可するかの判定には GS2-Script を用いて判定することができ、この関数の引数として指定したスクリプトには<br>
	 * - 実行するアクション(Gs2Stamina:ChangeStamina)<br>
	 * - アクションの引数({'staminaPool': 'stamina-0001', 'variation': 50, 'maxValue': 50, 'overflow': false})<br>
	 * を引数として受け取り、バリデーションすることが出来ます。<br>
	 * <br>
	 * これらのアクションの実行が問題がない場合は result に true を返してください。<br>
	 * 
	 * @param request リクエストパラメータ
	 * @return ワンタイムトークン発行結果
	 */
	public CreateOnceOnetimeTokenResult createOnceOntimeToken(CreateOnceOnetimeTokenRequest request) {
		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("scriptName", request.getScriptName())
				.put("grant", request.getGrant())
				.put("args", request.getArgs().toString(2));
		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/onetime/once/token", 
				credential, 
				ENDPOINT,
				CreateOnceOnetimeTokenRequest.Constant.MODULE, 
				CreateOnceOnetimeTokenRequest.Constant.FUNCTION,
				body.toString());
		return doRequest(post, CreateOnceOnetimeTokenResult.class);
	}
}
