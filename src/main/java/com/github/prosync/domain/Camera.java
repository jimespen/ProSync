package com.github.prosync.domain;

/**
 * Created by jim-espen on 10/10/14.
 */
public class Camera {

        private String nic;
        final private String camName;
        private boolean selected;
        private String password;

        public Camera(String camName) {
            this.camName = camName;
        }
        
        public void setNic(String nic){
            this.nic = nic;
        }

        public String getNic(){
            return nic;
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