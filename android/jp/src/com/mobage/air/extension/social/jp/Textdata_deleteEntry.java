/*******************************************************************************
 * The MIT License (MIT)
 * Copyright (c) 2013 DeNA Co., Ltd.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package com.mobage.air.extension.social.jp;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.mobage.air.extension.ArgsParser;
import com.mobage.air.extension.Dispatcher;
import com.mobage.android.Error;

public class Textdata_deleteEntry implements FREFunction {

	@Override
	public FREObject call(final FREContext context, FREObject[] args) {
		try {
			ArgsParser a = new ArgsParser(args);
			final String groupName = a.nextString();
			final String entryId = a.nextString();
			final String onSuccessId = a.nextString();
			final String onErrorId = a.nextString();
			a.finish();

			com.mobage.android.social.jp.Textdata.OnUpdateEntryComplete cb = new com.mobage.android.social.jp.Textdata.OnUpdateEntryComplete() {
				@Override
				public void onSuccess() {
					Dispatcher.dispatch(context, onSuccessId);
				}

				@Override
				public void onError(Error error) {
					Dispatcher.dispatch(context, onErrorId, error);
				}
			};

			com.mobage.android.social.jp.Textdata.deleteEntry(groupName, entryId, cb);
		} catch (Exception e) {
			Dispatcher.exception(context, e);
		}
		return null;
	}
}
