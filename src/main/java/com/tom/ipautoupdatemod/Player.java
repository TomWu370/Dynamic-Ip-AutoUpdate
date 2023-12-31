package com.tom.ipautoupdatemod;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

public class Player {
    private boolean isLatest;
// private key pairs


    public String getNewURL(){
        // SpringRequester.getIP;
        // return ip, if not whitelisted or not found then return null
        // decrypt
        return "url";
    }

    public String[] ipAddress(){
        // database will store both the current url this account is using, and the current new url encrypted
        // using the account's public key, during the check if both encrypted message are the same, then
        // means same url
        // database will return true or false for whether current url is the newest
        // true or false, will return both url
        // then set current private variable, isLatest to true
        // updateServerList will only update when this variable is false
        return new String[]{"mc.hypixel.net", "mc.test.net"};}


    public void updateServerList(ServerList servers) {
        if (this.getNewURL() != null) {
            String[] ipPair = this.ipAddress();
            String previousIP = ipPair[0];
            String newIP = ipPair[1];

            servers.load();
            Object[] returnData = findServer(servers, previousIP);
            if (returnData[0] != null || returnData[1] != null) {

                int index = (int) returnData[0];
                ServerData newData = (ServerData) returnData[1];
                newData.ip = newIP;
                servers.replace(index, newData);
                servers.save();
            }
        }
    }


    public static Object[] findServer(ServerList servers, String previousIP){
        //Map<Integer, ServerData> datapair = new HashMap<>();
        for (int i = 0; i < servers.size(); i++){
            ServerData tempData = servers.get(i);
            if (tempData.ip.equals(previousIP)){
                return new Object[] { i, tempData };
            }
        }
        return new Object[] { null, null };
    }
}
