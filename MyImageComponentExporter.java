package com.aem.community.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = {
		ComponentExporter.class }, resourceType = { "AEMMaven13/components/content/myimage" })
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION, selector = ExporterConstants.SLING_MODEL_SELECTOR)
@JsonSerialize(as = MyImageComponentExporter.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyImageComponentExporter implements Validatable, ComponentExporter {
	static final String RESOURCE_TYPE = "AEMMaven13/components/content/myimage";

	@SlingObject
	Resource resource;

	@ChildResource(name = "coverImage")
	ImageModel coverImage;

	private Validation validation;

	@Override
	public String getExportedType() {
		return RESOURCE_TYPE;
	}

	public ImageModel getCoverImage() {
		return coverImage;
	}

	private static boolean isImageAssetEmpty(ImageModel imageModel) {
		return imageModel == null || imageModel.getAsset() == null;
	}

	@Override
	public Validation getValidation() {
		return validation;
	}

	public String getMyCustomeProperty() {
		return "mycustompropertyvalue";
	}

}
