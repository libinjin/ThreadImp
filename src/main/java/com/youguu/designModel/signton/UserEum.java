package com.youguu.designModel.signton;

public class UserEum {

    private UserEum(){}

    public static UserEum getInstance(){
        return SingletonUserEum.INSTANCE.getInstance();
    }

    static enum SingletonUserEum{

        INSTANCE;
        private UserEum useEum;
        private SingletonUserEum(){
            UserEum useEum = new UserEum();
        }

        public UserEum getInstance(){
            return this.useEum;
        }
    }

    public static void main(String[] args) {

        UserEum u1 = UserEum.getInstance();
        UserEum u2 = UserEum.getInstance();
        System.out.println(u1 == u2);
    }
}
