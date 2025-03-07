/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Changes from Qualcomm Innovation Center are provided under the following license:
 * Copyright (c) 2022 Qualcomm Innovation Center, Inc. All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause-Clear
 *
*/

package com.android.bluetooth.mapclient;

import com.android.vcard.VCardEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Object representation of message in bMessage format
 * <p>
 * This object will be received in {@link MasClient#EVENT_GET_MESSAGE}
 * callback message.
 */
public class Bmessage {

    String mBmsgVersion;
    Status mBmsgStatus;
    Type mBmsgType;
    String mBmsgFolder;

    String mBbodyEncoding;
    String mBbodyCharset;
    String mBbodyLanguage;
    int mBbodyLength;
    String MimeType;
    boolean mImageMMS = false;

    String mMessage;

    ArrayList<VCardEntry> mOriginators;
    ArrayList<VCardEntry> mRecipients;

    /**
     * Constructs empty message object
     */
    public Bmessage() {
        mOriginators = new ArrayList<VCardEntry>();
        mRecipients = new ArrayList<VCardEntry>();
    }

    public VCardEntry getOriginator() {
        if (mOriginators.size() > 0) {
            return mOriginators.get(0);
        } else {
            return null;
        }
    }

    public ArrayList<VCardEntry> getOriginators() {
        return mOriginators;
    }

    public Bmessage addOriginator(VCardEntry vcard) {
        mOriginators.add(vcard);
        return this;
    }

    public ArrayList<VCardEntry> getRecipients() {
        return mRecipients;
    }

    public Bmessage addRecipient(VCardEntry vcard) {
        mRecipients.add(vcard);
        return this;
    }

    public Status getStatus() {
        return mBmsgStatus;
    }

    public Bmessage setStatus(Status status) {
        mBmsgStatus = status;
        return this;
    }

    public Type getType() {
        return mBmsgType;
    }

    public Bmessage setType(Type type) {
        mBmsgType = type;
        return this;
    }

    public String getFileType() {
        return MimeType;
    }

    public Bmessage setFileType(String FileType){
        MimeType = FileType;
        return this;
    }
    public Bmessage SetImageMMS(boolean flag){
        mImageMMS = flag;
        return this;
    }

    public boolean IsImageMMS() {
        return mImageMMS;
    }

    public String getFolder() {
        return mBmsgFolder;
    }

    public Bmessage setFolder(String folder) {
        mBmsgFolder = folder;
        return this;
    }

    public String getEncoding() {
        return mBbodyEncoding;
    }

    public Bmessage setEncoding(String encoding) {
        mBbodyEncoding = encoding;
        return this;
    }

    public String getCharset() {
        return mBbodyCharset;
    }

    public Bmessage setCharset(String charset) {
        mBbodyCharset = charset;
        return this;
    }

    public String getLanguage() {
        return mBbodyLanguage;
    }

    public Bmessage setLanguage(String language) {
        mBbodyLanguage = language;
        return this;
    }

    public String getBodyContent() {
        return mMessage;
    }

    public Bmessage setBodyContent(String body) {
        mMessage = body;
        return this;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();

        try {
            json.put("status", mBmsgStatus);
            json.put("type", mBmsgType);
            json.put("folder", mBmsgFolder);
            json.put("charset", mBbodyCharset);
            json.put("message", mMessage);
        } catch (JSONException e) {
            // do nothing
        }

        return json.toString();
    }

    public enum Status {
        READ, UNREAD
    }

    public enum Type {
        EMAIL, SMS_GSM, SMS_CDMA, MMS
    }
}
