/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.shadowmask.test

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import org.junit.Test
import org.junit.Assert._

/**
  * Created by liyh on 16/9/20.
  */
class TestMd5 {

  @Test
  def testMd5() = {
    val md5 = MessageDigest.getInstance("MD5")
    val str = new String(Hex.encodeHex(md5.digest("shadowmask".getBytes)))
    assertTrue(str=="ad98bea499d76c4bccbaeb3e08e863d4")
  }
}
