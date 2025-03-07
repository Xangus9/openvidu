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

package io.openvidu.test.browsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxUser extends BrowserUser {

	public FirefoxUser(String userName, int timeOfWaitInSeconds, boolean disableOpenH264) {
		super(userName, timeOfWaitInSeconds);

		String REMOTE_URL = System.getProperty("REMOTE_URL_FIREFOX");

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setAcceptInsecureCerts(true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

		FirefoxProfile profile = new FirefoxProfile();

		// This flag avoids granting the access to the camera
		profile.setPreference("media.navigator.permission.disabled", true);
		// This flag force to use fake user media (synthetic video of multiple color)
		profile.setPreference("media.navigator.streams.fake", true);

		if (disableOpenH264) {
			profile.setPreference("media.gmp-gmpopenh264.enabled", false);
		}

		capabilities.setCapability(FirefoxDriver.PROFILE, profile);

		FirefoxOptions options = new FirefoxOptions(capabilities);

		if (REMOTE_URL != null) {
			options.setHeadless(true);
			log.info("Using URL {} to connect to remote web driver", REMOTE_URL);
			try {
				this.driver = new RemoteWebDriver(new URL(REMOTE_URL), options);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			log.info("Using local web driver");
			this.driver = new FirefoxDriver(options);
		}

		this.driver.manage().timeouts().setScriptTimeout(timeOfWaitInSeconds, TimeUnit.SECONDS);
		this.configureDriver(new org.openqa.selenium.Dimension(1920, 1080));
	}

}