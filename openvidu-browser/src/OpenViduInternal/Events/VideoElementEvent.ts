/*
 * (C) Copyright 2017-2022 OpenVidu (https://openvidu.io)
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

import { Event } from './Event';
import { StreamManager } from '../../OpenVidu/StreamManager';

/**
 * Triggered by:
 * - [[videoElementCreated]]
 * - [[videoElementDestroyed]]
 */
export class VideoElementEvent extends Event {
    /**
     * Video element that was created or destroyed
     */
    element: HTMLVideoElement;

    /**
     * @hidden
     */
    constructor(element: HTMLVideoElement, target: StreamManager, type: string) {
        super(false, target, type);
        this.element = element;
    }

    /**
     * @hidden
     */
    // tslint:disable-next-line:no-empty
    callDefaultBehavior() {}
}
