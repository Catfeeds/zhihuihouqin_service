/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.lc.model.easeui.init.domain;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "m_im_hxuserinfo")
public class M_UserInfo_IM implements Serializable {

    /**
     * "userId": 1,
     * "name": "ABCD1",
     * "userName": "你好啊1",
     * "userAvatar": "20160914164245413c645259fa8d347528c28a4834d532fce.jpg"
     */
    @Id
    public int id;
    public String userId;//": 1,
    public String name,//": "ABCD1",
            userName,//": "你好啊1",
            userAvatar;//": "20160914164245413c645259fa8d347528c28a4834d532fce.jpg"
}



