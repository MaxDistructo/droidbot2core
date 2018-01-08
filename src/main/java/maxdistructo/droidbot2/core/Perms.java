package maxdistructo.droidbot2.core;


import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.PermissionUtils;

import static maxdistructo.droidbot2.core.Client.client;

public class Perms {
    public static boolean checkMod(IMessage message){
        IUser author = message.getAuthor();
        //Checks if user is a Moderator of the server
        long[] moderators = Config.readServerModConfig(message.getGuild());
        int i = 0;
        while(i < moderators.length){
            if(author.getLongID() == moderators[i] || checkAdmin(message) ||  checkOwner_Guild(message)){
                return true;
            }
            i++;
        }
        return false;
    }

    public static boolean checkAdmin(IMessage message){
        IUser author = message.getAuthor();
        //Checks if user is a Admin/Owner of the Server (Or Myself).
        long[] admins = Config.readServerAdminConfig(message.getGuild());
        int i = 0;
        while(i < admins.length){
            if(author.getLongID() == admins[i] || checkOwner_Guild(message)){
                return true;
            }
            i++;
        }
        return false;
    }

    public static boolean checkOwner_Guild(IMessage message){
        IUser author = message.getAuthor();

        return author.getLongID() == message.getGuild().getOwnerLongID()|| checkOwner(message);
    }
    public static boolean checkOwner(IMessage message){
       IUser author = message.getAuthor();
        return author.getLongID() == client.getApplicationOwner().getLongID() || author.getLongID() == 374517920505790464L;
    }

    public static boolean checkGames(IMessage message){
        IChannel channel = message.getChannel();
        String channelName = channel.getName();

        String[] channels = Config.readServerGamesConfig(message.getGuild());
        int i = 0;
        while(i < channels.length){
            if(channelName.equals(channels[i])){
                return true;
            }
            i++;
        }
        return false;
    }
    public static boolean checkForPermission(IMessage message, Permissions permission){
        return PermissionUtils.hasPermissions(message.getGuild(), message.getAuthor(), permission);
    }
}
