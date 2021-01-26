package com.pi4j.plugin.pigpio.provider.pwm;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: PLUGIN   :: PIGPIO I/O Providers
 * FILENAME      :  PiGpioPwmProviderImpl.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2021 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmProviderBase;
import com.pi4j.io.pwm.PwmType;
import com.pi4j.library.pigpio.PiGpio;

import java.io.IOException;

/**
 * <p>PiGpioPwmProviderImpl class.</p>
 *
 * @author Robert Savage (<a href="http://www.savagehomeautomation.com">http://www.savagehomeautomation.com</a>)
 * @version $Id: $Id
 */
public class PiGpioPwmProviderImpl extends PwmProviderBase implements PiGpioPwmProvider {

    final PiGpio piGpio;

    /**
     * <p>Constructor for PiGpioPwmProviderImpl.</p>
     *
     * @param piGpio a {@link com.pi4j.library.pigpio.PiGpio} object.
     * @throws java.io.IOException if any.
     */
    public PiGpioPwmProviderImpl(PiGpio piGpio) throws IOException {
        this.id = ID;
        this.name = NAME;
        this.piGpio = piGpio;
    }

    /** {@inheritDoc} */
    @Override
    public Pwm create(PwmConfig config) throws Exception {
        // initialize the PIGPIO library
        if(!piGpio.isInitialized()) piGpio.initialize();

        // create new I/O instance based on I/O config
        if(config.pwmType() == PwmType.HARDWARE){
            return new PiGpioPwmHardware(piGpio, this, config);
        }
        else {
            return new PiGpioPwmSoftware(piGpio, this, config);
        }
    }
}
