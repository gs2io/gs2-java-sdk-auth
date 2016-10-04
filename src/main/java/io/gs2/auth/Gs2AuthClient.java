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
import io.gs2.auth.control.LoginRequest;
import io.gs2.auth.control.LoginResult;
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
}
