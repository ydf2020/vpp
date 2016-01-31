/*
 * Copyright (c) 2015 Cisco and/or its affiliates.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openvpp.vppjapi;

public final class vppVxlanTunnelDetails {
    public final int srcAddress;
    public final int dstAddress;
    public final int encapVrfId;
    public final int vni;
    public final int decapNextIndex;

    public vppVxlanTunnelDetails(int srcAddress, int dstAddress,
            int encapVrfId, int vni, int decapNextIndex) {
        this.srcAddress = srcAddress;
        this.dstAddress = dstAddress;
        this.encapVrfId = encapVrfId;
        this.vni = vni;
        this.decapNextIndex = decapNextIndex;
    }
}
