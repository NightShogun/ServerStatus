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
import org.bukkit.Bukkit;

public class ClientBoundStatusInterceptor implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent e) {

        synchronized (this){
            if (e.getPacketType() == PacketType.Handshaking.Client.HANDSHAKE) {

                WrapperHandshakingClientHandshake wrapperPacket = new WrapperHandshakingClientHandshake(e);

                wrapperPacket.setProtocolVersion(763);
                wrapperPacket.setClientVersion(ClientVersion.V_1_20);
            }

            if (e.getPacketType() == PacketType.Status.Client.PING) {
                WrapperStatusClientPing wrapperStatusClientPing = new WrapperStatusClientPing(e);

                wrapperStatusClientPing.setClientVersion(ClientVersion.V_1_20);
            }
        }

    }

    @Override
    public void onPacketSend(PacketSendEvent e) {

        if(e.getPacketType() == PacketType.Status.Server.RESPONSE){

            WrapperStatusServerResponse wrappedPacket = new WrapperStatusServerResponse(e);

            wrappedPacket.setComponent(packetObj);
        }

        if(e.getPacketType() == PacketType.Status.Server.PONG){

            System.out.println("[DEBUG] OUTBOUND ignited");

            WrapperStatusServerPong wrapperStatusServerPong = new WrapperStatusServerPong(e);
            wrapperStatusServerPong.setClientVersion(ClientVersion.V_1_20);

        }
    }
}
