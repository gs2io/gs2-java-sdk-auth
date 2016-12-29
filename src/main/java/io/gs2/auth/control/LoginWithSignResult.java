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
 * ログイン結果。
 * 
 * @author Game Server Services, Inc.
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginWithSignResult {

	/** アクセストークン */
	String token;
	/** サービスID */
	String serviceId;
	/** ユーザID */
	String userId;
	/** アクセストークンの有効期限 */
	Long expire;

	/**
	 * アクセストークンを取得
	 * 
	 * @return アクセストークン
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * アクセストークンを設定
	 * 
	 * @param token アクセストークン
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * サービスIDを取得
	 * 
	 * @return サービスID
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * サービスIDを設定
	 * 
	 * @param serviceId サービスID
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * ユーザIDを取得
	 * 
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 * 
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * アクセストークンの有効期限を取得
	 * 
	 * @return アクセストークンの有効期限
	 */
	public Long getExpire() {
		return expire;
	}

	/**
	 * アクセストークンの有効期限を設定
	 * 
	 * @param expire アクセストークンの有効期限
	 */
	public void setExpire(Long expire) {
		this.expire = expire;
	}
}
