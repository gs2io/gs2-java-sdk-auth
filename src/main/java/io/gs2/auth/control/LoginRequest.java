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
 * ログインリクエスト。
 * 
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
public class LoginRequest extends Gs2BasicRequest<LoginRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "Login";
	}

	/** サービスID */
	String serviceId;
	/** ユーザID */
	String userId;

	/**
	 * サービスIDを取得。
	 * 
	 * @return サービスID
	 */
	public String getServiceId() {
		return serviceId;
	}
	
	/**
	 * サービスIDを設定。
	 * 
	 * @param serviceId サービスID
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	/**
	 * サービスIDを設定。
	 * 
	 * @param serviceId サービスID
	 * @return this
	 */
	public LoginRequest withServiceId(String serviceId) {
		setServiceId(serviceId);
		return this;
	}
	
	/**
	 * ユーザIDを取得。
	 * 
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * ユーザIDを設定。
	 * 
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * ユーザIDを設定。
	 * 
	 * @param userId ユーザID
	 * @return this
	 */
	public LoginRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}
}
