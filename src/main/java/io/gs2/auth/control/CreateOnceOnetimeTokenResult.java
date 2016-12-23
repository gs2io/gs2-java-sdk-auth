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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 1回のみ有効ワンタイムトークン発行結果。
 * 
 * @author Game Server Services, Inc.
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateOnceOnetimeTokenResult {

	/** ワンタイムトークン */
	String token;

	/**
	 * ワンタイムトークンを取得
	 * 
	 * @return ワンタイムトークン
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * ワンタイムトークンを設定
	 * 
	 * @param token ワンタイムトークン
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
