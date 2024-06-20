package xyz.skylar11d.minecraftp.serverstatus.listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import com.github.retrooper.packetevents.wrapper.status.server.WrapperStatusServerResponse;
import com.google.gson.JsonObject;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.utilities.builder.StatusPacket;
import xyz.skylar11d.minecraftp.serverstatus.utilities.configuration.type.ConfigType;

import java.util.Optional;

public class ClientBoundStatusInterceptor implements PacketListener {

    //private final Main main;
    private final Optional<YamlConfiguration> properties;

    public ClientBoundStatusInterceptor(Main instance){
        //this.main = instance;
        this.properties = instance.getProvider().getConfigManager().getConfig(ConfigType.properties);
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {

        synchronized (this){

            if(!(event.getPacketType() == PacketType.Status.Server.RESPONSE)) return;

            WrapperStatusServerResponse wrappedPacket = new WrapperStatusServerResponse(event);

            String motd = properties.orElseThrow().getString("motd.text");
            String verMsg = properties.orElseThrow().getString("version.display.text");
            String hover = properties.orElseThrow().getString("version.display.hover");
            int protocol = properties.orElseThrow().getInt("version.protocol");

            buildPacket(motd, verMsg, hover, protocol, wrappedPacket);

        }

    }


    private void buildPacket(String motd, String verMsg, String hover, int protocol, WrapperStatusServerResponse response){

        JsonObject mutated = new StatusPacket()
                .version(verMsg, protocol)
                    .players(hover)
                        .motd(motd)
                            .toJSON();

        response.setComponent(mutated);

    }



    @Override
    public void onPacketReceive(PacketReceiveEvent e) {
        synchronized (this) {
            if (e.getPacketType() == PacketType.Handshaking.Client.HANDSHAKE) {

                WrapperHandshakingClientHandshake wrapperPacket = new WrapperHandshakingClientHandshake(e);

                wrapperPacket.setProtocolVersion(ClientVersion.V_1_20.getProtocolVersion());
                wrapperPacket.setClientVersion(ClientVersion.V_1_20);

            }
        }

    }
}
