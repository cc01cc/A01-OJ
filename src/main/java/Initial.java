/*
 *        Initial.java  Copyright (c) 2021. cc01cc. All rights reserved.
 *
 *        Licensed under the Apache License, Version 2.0 (the "License");
 *        you may not use this file except in compliance with the License.
 *        You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *        Unless required by applicable law or agreed to in writing, software
 *        distributed under the License is distributed on an "AS IS" BASIS,
 *        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *        See the License for the specific language governing permissions and
 *        limitations under the License.
 */

import java.util.prefs.Preferences;

/**
 * @author cc01cc
 */
public class Initial {
    public static void main(String[] args){
        Initial initial = new Initial();

        initial.initialPreferences();
    }

    protected void initialPreferences(){
        Preferences prefs = Preferences.userNodeForPackage(Initial.class);
        prefs.put("excelPath", System.getProperty(System.getProperty("user.dir") + "\\src\\main\\resources"));
    }
}
