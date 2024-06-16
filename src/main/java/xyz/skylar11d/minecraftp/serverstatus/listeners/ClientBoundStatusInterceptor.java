package xyz.skylar11d.minecraftp.serverstatus.listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import com.github.retrooper.packetevents.wrapper.status.client.WrapperStatusClientPing;
import com.github.retrooper.packetevents.wrapper.status.server.WrapperStatusServerPong;
import com.github.retrooper.packetevents.wrapper.status.server.WrapperStatusServerResponse;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import xyz.skylar11d.minecraftp.serverstatus.Main;
import xyz.skylar11d.minecraftp.serverstatus.utilities.builder.StatusPacket;

public class ClientBoundStatusInterceptor implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent e) {

        //Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> {
            if (e.getPacketType() == PacketType.Handshaking.Client.HANDSHAKE) {

                WrapperHandshakingClientHandshake wrapperPacket = new WrapperHandshakingClientHandshake(e);

                wrapperPacket.setProtocolVersion(763);
                wrapperPacket.setClientVersion(ClientVersion.V_1_20);

            }
        //});

    }
}
