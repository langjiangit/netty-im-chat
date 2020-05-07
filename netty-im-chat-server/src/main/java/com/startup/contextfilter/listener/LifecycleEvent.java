/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.startup.contextfilter.listener;



import com.startup.contextfilter.subject.Lifecycle;

import java.util.EventObject;


/**
 * LifecycleEvent类表示生命周期中的某个事件
 * 生命周期事件，对主题与发生的事件进行封装。
 */
public final class LifecycleEvent extends EventObject {

    private static final long serialVersionUID = 1L;


    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        super(lifecycle);
        this.type = type;
        this.data = data;
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The event data associated with this event.
     */
    private Object data = null;


    /**
     * The event type this instance represents.
     */
    private String type = null;



    /**
     * Return the event data of this event.
     */
    public Object getData() {
        return (this.data);
    }


    /**
     * Return the Lifecycle on which this event occurred.
     */
    public Lifecycle getLifecycle() {
        return (Lifecycle) getSource();
    }


    /**
     * Return the event type of this event.
     */
    public String getType() {
        return (this.type);
    }


}
