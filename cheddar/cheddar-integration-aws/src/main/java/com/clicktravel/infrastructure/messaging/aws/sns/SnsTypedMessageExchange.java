/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.infrastructure.messaging.aws.sns;

import com.clicktravel.cheddar.infrastructure.messaging.TypedMessage;

public class SnsTypedMessageExchange extends SnsExchange<TypedMessage> {

    public SnsTypedMessageExchange(final SnsTopicResource snsTopicResource) {
        super(snsTopicResource);
    }

    @Override
    protected SnsSubjectAndMessage toSnsSubjectAndMessage(final TypedMessage typedMessage) {
        return new SnsSubjectAndMessage() {
            @Override
            public String getSubject() {
                return typedMessage.getType();
            }

            @Override
            public String getMessage() {
                return typedMessage.getPayload();
            }
        };
    }

}
