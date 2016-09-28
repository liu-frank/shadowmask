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

package org.shadowmask.web.api

import org.json4s._
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.servlet.FileUploadSupport
import org.scalatra.swagger._
import org.shadowmask.web.common.user.{ConfiguredAuthProvider, User}
import org.shadowmask.web.model.{LoginResult, LoginResultData}

class AdminApi(implicit val swagger: Swagger) extends ScalatraServlet
  with FileUploadSupport
  with JacksonJsonSupport
  with SwaggerSupport
  with ConfiguredAuthProvider {

  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "AdminApi"
  override protected val applicationName: Option[String] = Some("admin")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }


  val adminLoginPostOperation = (apiOperation[LoginResult]("adminLoginPost")
    summary "Adminstrator login api"
    parameters(formParam[String]("username").description("administrator'name")
    , formParam[String]("password").description("administrator'password"))
    )

  post("/admin/login", operation(adminLoginPostOperation)) {
    val username = params.getAs[String]("username")
    val password = params.getAs[String]("password")
    val (code, info, loginData) =
      getAuth().auth(Some(User(username.getOrElse(""), password.getOrElse("")))) match {
        case Some(token) => (Some(0), Some("successfully"), Some(LoginResultData(Some(token.token))))
        case _ => (Some(1), Some("failed"), Some(LoginResultData(Some(""))))
      }
    LoginResult(code, info, loginData)
  }

}
