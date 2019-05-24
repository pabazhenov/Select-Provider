/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonPOJO;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Bazhenov_PA
 */
public class JsonRoutePOJO {
    @SerializedName("response")
    private Root jsonroot;

    public Root getJsonroot() {
        return jsonroot;
    }

    public void setJsonroot(Root jsonroot) {
        this.jsonroot = jsonroot;
    }

}
