package priv.resource.io.nio.mina.minaServer;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class MathProtocolCodecFactory extends DemuxingProtocolCodecFactory {

	public MathProtocolCodecFactory(boolean server) {
		if (server) {
			super.addMessageDecoder(BaseMessageDecoder.class);
			super.addMessageEncoder(getClass(), BaseMessageEncoder.class);
		}
	}
}