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
public class LoginWithSignRequest extends Gs2BasicRequest<LoginWithSignRequest> {

	public static class Constant extends Gs2Auth.Constant {
		public static final String FUNCTION = "LoginWithSign";
	}

	/** サービスID */
	String serviceId;
	/** ユーザID */
	String userId;
	/** 暗号鍵名 */
	String keyName;
	/** GS2-Account認証署名 */
	String sign;

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
	public LoginWithSignRequest withServiceId(String serviceId) {
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
	public LoginWithSignRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}
	
	/**
	 * 暗号鍵名を取得。
	 * 
	 * @return 暗号鍵名
	 */
	public String getKeyName() {
		return keyName;
	}
	
	/**
	 * 暗号鍵名を設定。
	 * 
	 * @param keyName 暗号鍵名
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	/**
	 * 暗号鍵名を設定。
	 * 
	 * @param keyName 暗号鍵名
	 * @return this
	 */
	public LoginWithSignRequest withKeyName(String keyName) {
		setKeyName(keyName);
		return this;
	}
	
	/**
	 * GS2-Account認証署名を取得。
	 * 
	 * @return GS2-Account認証署名
	 */
	public String getSign() {
		return sign;
	}
	
	/**
	 * GS2-Account認証署名を設定。
	 * 
	 * @param sign GS2-Account認証署名
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * GS2-Account認証署名を設定。
	 * 
	 * @param sign GS2-Account認証署名
	 * @return this
	 */
	public LoginWithSignRequest withSign(String sign) {
		setSign(sign);
		return this;
	}
}
