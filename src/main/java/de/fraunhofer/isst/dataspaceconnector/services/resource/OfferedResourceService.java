package de.fraunhofer.isst.dataspaceconnector.services.resource;

import de.fraunhofer.iais.eis.Resource;
import de.fraunhofer.isst.dataspaceconnector.model.OfferedResource;
import de.fraunhofer.isst.dataspaceconnector.model.resource.ResourceMetadata;
import de.fraunhofer.isst.dataspaceconnector.model.resource.ResourceRepresentation;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * <p>OfferedResourceService interface.</p>
 *
 * @author Julia Pampus
 * @version $Id: $Id
 */
public interface OfferedResourceService {
    /**
     * <p>getResourceList.</p>
     *
     * @return a {@link java.util.ArrayList} object.
     */
    ArrayList<Resource> getResourceList();

    /**
     * <p>getOfferedResources.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<UUID, Resource> getOfferedResources();

    /**
     * <p>addResource.</p>
     *
     * @param resourceMetadata a {@link ResourceMetadata} object.
     * @return a {@link java.util.UUID} object.
     */
    UUID addResource(ResourceMetadata resourceMetadata);

    /**
     * <p>addResourceWithId.</p>
     *
     * @param resourceMetadata a {@link ResourceMetadata} object.
     * @param uuid a {@link java.util.UUID} object.
     */
    void addResourceWithId(ResourceMetadata resourceMetadata, UUID uuid);

    /**
     * <p>addData.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param data a {@link java.lang.String} object.
     */
    void addData(UUID resourceId, String data);

    /**
     * <p>updateResource.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param resourceMetadata a {@link ResourceMetadata} object.
     */
    void updateResource(UUID resourceId, ResourceMetadata resourceMetadata);

    /**
     * <p>updateContract.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param policy a {@link java.lang.String} object.
     */
    void updateContract(UUID resourceId, String policy);

    /**
     * <p>deleteResource.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     */
    void deleteResource(UUID resourceId);

    /**
     * <p>getResource.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @return a {@link de.fraunhofer.isst.dataspaceconnector.model.OfferedResource} object.
     */
    OfferedResource getResource(UUID resourceId);

    /**
     * <p>getMetadata.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @return a {@link ResourceMetadata} object.
     */
    ResourceMetadata getMetadata(UUID resourceId);

    /**
     * <p>getData.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @return a {@link java.lang.String} object.
     * @throws java.lang.Exception if any.
     */
    String getData(UUID resourceId) throws Exception;

    /**
     * <p>getDataByRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representationId a {@link java.util.UUID} object.
     * @return a {@link java.lang.String} object.
     * @throws java.lang.Exception if any.
     */
    String getDataByRepresentation(UUID resourceId, UUID representationId) throws Exception;

    /**
     * <p>addRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representation a {@link ResourceRepresentation} object.
     * @return a {@link java.util.UUID} object.
     */
    UUID addRepresentation(UUID resourceId, ResourceRepresentation representation);

    /**
     * <p>addRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representation a {@link ResourceRepresentation} object.
     * @param representationId the {@link UUID} that will be used for the new representation
     * @return a {@link java.util.UUID} object.
     */
    UUID addRepresentationWithId(UUID resourceId, ResourceRepresentation representation, UUID representationId);

    /**
     * <p>updateRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representationId a {@link java.util.UUID} object.
     * @param representation a {@link ResourceRepresentation} object.
     */
    void updateRepresentation(UUID resourceId, UUID representationId, ResourceRepresentation representation);

    /**
     * <p>getRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representationId a {@link java.util.UUID} object.
     * @return a {@link ResourceRepresentation} object.
     */
    ResourceRepresentation getRepresentation(UUID resourceId, UUID representationId);

    /**
     * <p>deleteRepresentation.</p>
     *
     * @param resourceId a {@link java.util.UUID} object.
     * @param representationId a {@link java.util.UUID} object.
     */
    void deleteRepresentation(UUID resourceId, UUID representationId);
}
