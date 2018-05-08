package maxdistructo.discord.core.message

/*
This code borrows some of the IWebhook handling code from sx.blah.discord.handle.impl.obj.Guild.java
The above mentioned code was released under a GNU Lesser General Public License at https://github.com/Discord4J/Discord4J
The only modification of this code was a convert to Kotlin.

WARNING: This is UNTESTED and HIGHLY EXPERIMENTAL CODE!
*/

import com.mashape.unirest.http.Unirest
import maxdistructo.discord.core.Utils
import maxdistructo.discord.core.impl.Bot
import sx.blah.discord.api.internal.DiscordClientImpl
import sx.blah.discord.api.internal.DiscordEndpoints
import sx.blah.discord.api.internal.json.objects.WebhookObject
import maxdistructo.discord.core.impl.Webhook
import org.json.JSONArray
import org.json.JSONObject
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.RequestBuffer

object IWebhook{

fun createWebhook(channel : IChannel, name: String, avatar : String){
  channel.createWebhook(name, avatar)
}
fun name(channel : IChannel, name : String, newName : String){
  val webhook = channel.getWebhooksByName(name) [0]
  webhook.changeDefaultName(newName)
}

fun avatar(channel : IChannel, name : String, avatar : String){
  val webhook = channel.getWebhooksByName(name) [0]
  webhook.changeDefaultAvatar(avatar)
}
    fun jsonBuilder(webhook : Webhook) : JSONObject{
        val out = JSONObject()
        return out
    }
  fun send(bot : Bot, channel : IChannel, name : String, message : String){
   val webhook = getByName(bot, channel, name)
   Unirest.post(DiscordEndpoints.WEBHOOKS + webhook.id + "/" + webhook.token).body(JSONArray())
  }
fun getByName(bot : Bot, channel : IChannel, name : String) : Webhook{
  var webhookList = listOf<Webhook>()
   lateinit var webhookObjects : Array<WebhookObject>
    val client = bot.client as DiscordClientImpl
  val webhooks = RequestBuffer.request {
      webhookObjects = client.REQUESTS.GET.makeRequest(
              DiscordEndpoints.CHANNELS + channel.longID + "/webhooks",
              Array<WebhookObject>::class.java)
  }
  lateinit var webhook : Webhook
    for(value in webhookObjects){
        webhookList += arrayOf(Webhook(bot.client, value.name, Utils.convertToLong(value.id)!!, bot.client.getChannelByID(Utils.convertToLong(value.channel_id)!!), bot.client.getUserByID(Utils.convertToLong(value.user.id)!!), value.avatar, value.token))
    }
  
  for(value in webhookList){
    if(value.defaultName == name){
      webhook = value
    }
  }
    return webhook
  
  
}
    
}
