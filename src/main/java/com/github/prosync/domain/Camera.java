package com.github.prosync.domain;

import java.net.NetworkInterface;

/**
 * Class Camera, Object Class to store information about Cameras
 */
public class Camera {

        private NetworkInterface nic;
        final private String camName;
        private boolean selected;
        private String password;

        public Camera(String camName) {
            this.camName = camName;
        }

    public NetworkInterface getNic() {
        return nic;
    }

    public void setNic(NetworkInterface nic) {
        this.nic = nic;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getCamName(){
            return camName;
        }
        
        public void setSelected(boolean selected){
            this.selected = selected;
        }
        
        public boolean getSelected(){
            return selected;
        }
        
        public void setPassword(String password){
            this.password = password;
        }
        
        public String getPassword(){
            return password;
        }

    }