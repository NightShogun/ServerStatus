package xyz.skylar11d.minecraftp.serverstatus.listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.status.server.WrapperStatusServerResponse;
import org.bukkit.Bukkit;

public class ClientBoundStatusInterceptor implements PacketListener {

    @Override
    public void onPacketSend(PacketSendEvent e) {

        if(e.getPacketType() == PacketType.Status.Server.RESPONSE) {

            WrapperStatusServerResponse wrappedPacket = new WrapperStatusServerResponse(e);

            Bukkit.getLogger().info(wrappedPacket.getComponentJson());

        }

    }
}
