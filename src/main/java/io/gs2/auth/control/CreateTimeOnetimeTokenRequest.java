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

import io.gs2.auth.Gs2Auth;
import io.gs2.control.Gs2BasicRequest;

/**
 * 有効期限付きワンタイムトークン発行リクエスト。
 * 
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
public class CreateTimeOnetimeTokenRequest extends Gs2BasicRequest<CreateTimeOnetimeTokenRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "CreateTimeOnetimeToken";
	}

	/** 認可スクリプト */
	String scriptName;

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
	public CreateTimeOnetimeTokenRequest withScriptName(String scriptName) {
		setScriptName(scriptName);
		return this;
	}
}
