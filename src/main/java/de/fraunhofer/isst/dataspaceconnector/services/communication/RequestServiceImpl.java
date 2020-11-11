package de.fraunhofer.isst.dataspaceconnector.services.communication;

import de.fraunhofer.iais.eis.*;
import de.fraunhofer.isst.ids.framework.configuration.ConfigurationContainer;
import de.fraunhofer.isst.ids.framework.messages.InfomodelMessageBuilder;
import de.fraunhofer.isst.ids.framework.messaging.core.handler.api.util.Util;
import de.fraunhofer.isst.ids.framework.spring.starter.IDSHttpService;
import de.fraunhofer.isst.ids.framework.spring.starter.TokenProvider;
import okhttp3.MultipartBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

/**
 * <p>MessageServiceImpl class.</p>
 *
 * @author Julia Pampus
 * @version $Id: $Id
 */
@Service
public class RequestServiceImpl implements RequestService {
    /** Constant <code>LOGGER</code> */
    public static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);

    private Connector connector;
    private TokenProvider tokenProvider;
    private IDSHttpService idsHttpService;

    @Autowired
    /**
     * <p>Constructor for MessageServiceImpl.</p>
     *
     * @param connector a {@link de.fraunhofer.iais.eis.Connector} object.
     * @param tokenProvider a {@link de.fraunhofer.isst.ids.framework.spring.starter.TokenProvider} object.
     * @param idsHttpService a {@link de.fraunhofer.isst.ids.framework.spring.starter.IDSHttpService} object.
     */
    public RequestServiceImpl(ConfigurationContainer configurationContainer, TokenProvider tokenProvider,
                              IDSHttpService idsHttpService) {
        this.connector = configurationContainer.getConnector();
        this.tokenProvider = tokenProvider;
        this.idsHttpService = idsHttpService;
    }

    /** {@inheritDoc} */
    @Override
    public Response sendLogMessage() throws IOException {
        LogMessage message = new LogMessageBuilder()
                ._issued_(Util.getGregorianNow())
                ._modelVersion_(connector.getOutboundModelVersion())
                ._issuerConnector_(connector.getId())
                ._senderAgent_(connector.getId())
                ._securityToken_(tokenProvider.getTokenJWS())
                .build();

        MultipartBody body = InfomodelMessageBuilder.messageWithString(message, "");
        LOGGER.info("TO CLEARING HOUSE | NOT SENT: " + body);
//        return idsHttpService.send(body, URI.create(recipient)); TODO send log messages
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Response sendNotificationMessage(String recipient) throws IOException {
        NotificationMessage message = new NotificationMessageBuilder()
                ._issued_(Util.getGregorianNow())
                ._modelVersion_(connector.getOutboundModelVersion())
                ._issuerConnector_(connector.getId())
                ._senderAgent_(connector.getId())
                ._securityToken_(tokenProvider.getTokenJWS())
                ._recipientConnector_(de.fraunhofer.iais.eis.util.Util.asList(URI.create(recipient)))
                .build();

        MultipartBody body = InfomodelMessageBuilder.messageWithString(message, "");
        LOGGER.info("TO PARTICIPANT | NOT SENT: " + body);
//        return idsHttpService.send(body, URI.create(recipient)); TODO send notification messages
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void sendContractAgreementMessage(ContractAgreement contractAgreement) throws IOException {
        String clearingHouse = "";
        ContractAgreementMessage message = new ContractAgreementMessageBuilder()
                ._securityToken_(tokenProvider.getTokenJWS())
                ._issued_(de.fraunhofer.isst.ids.framework.messaging.core.handler.api.util.Util.getGregorianNow())
                ._issuerConnector_(connector.getId())
                ._modelVersion_(connector.getOutboundModelVersion())
                ._senderAgent_(connector.getId())
                ._recipientConnector_(de.fraunhofer.iais.eis.util.Util.asList(URI.create(clearingHouse)))
                ._transferContract_(contractAgreement.getId())
                ._correlationMessage_(null)
                .build();

        MultipartBody body = InfomodelMessageBuilder.messageWithString(message, contractAgreement.toRdf());
        LOGGER.info("TO CLEARING HOUSE | NOT SENT: " + body);
//        return idsHttpService.send(body, URI.create(recipient)); TODO send contract agreement messages
    }
}
