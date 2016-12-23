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
package io.gs2.auth.control;

import org.json.JSONObject;

import io.gs2.auth.Gs2Auth;
import io.gs2.control.Gs2BasicRequest;

/**
 * 1回のみ有効ワンタイムトークン発行リクエスト。
 * 
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
public class CreateOnceOnetimeTokenRequest extends Gs2BasicRequest<CreateOnceOnetimeTokenRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "CreateOnceOnetimeToken";
	}

	/** 認可スクリプト */
	String scriptName;
	/** 認可アクション */
	String grant;
	/** 認可引数 */
	JSONObject args = new JSONObject();

	/**
	 * 認可スクリプトを取得。
	 * 
	 * @return 認可スクリプト
	 */
	public String getScriptName() {
		return scriptName;
	}
	
	/**
	 * 認可スクリプトを設定。
	 * 
	 * @param scriptName 認可スクリプト
	 */
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	
	/**
	 * 認可スクリプトを設定。
	 * 
	 * @param scriptName 認可スクリプト
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withScriptName(String scriptName) {
		setScriptName(scriptName);
		return this;
	}

	/**
	 * 認可アクションを取得。
	 * 
	 * @return 認可アクション
	 */
	public String getGrant() {
		return grant;
	}
	
	/**
	 * 認可アクションを設定。
	 * 
	 * @param grant 認可アクション
	 */
	public void setGrant(String grant) {
		this.grant = grant;
	}
	
	/**
	 * 認可アクションを設定。
	 * 
	 * @param grant 認可アクション
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withGrant(String grant) {
		setGrant(grant);
		return this;
	}

	/**
	 * 認可引数を取得。
	 * 
	 * @return 認可引数
	 */
	public JSONObject getArgs() {
		return args;
	}
	
	/**
	 * 認可引数を設定。
	 * 
	 * @param args 認可引数
	 */
	public void setArgs(JSONObject args) {
		this.args = args;
	}
	
	/**
	 * 認可引数を設定。
	 * 
	 * @param args 認可引数
	 * @return this
	 */
	public CreateOnceOnetimeTokenRequest withArgs(JSONObject args) {
		setArgs(args);
		return this;
	}
}
