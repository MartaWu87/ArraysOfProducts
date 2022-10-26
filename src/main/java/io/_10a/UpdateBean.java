package io._10a;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io._10a.controller.ProductsController;
import io._10a.entity.Products;

@Named
@ViewScoped
public class UpdateBean implements Serializable {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@EJB ProductsController controller;

	@Inject @Param(name = "id")
	private Long editedId;

	private Products product;

	@PostConstruct
	public void init() {
		logger.info("Passed product id: {}", editedId);
		product = controller.findById(editedId);
	}

	public Long getEditedId() {
		return editedId;
	}

	public void setEditedId(Long editedId) {
		this.editedId = editedId;
	}

	public Products getProduct() {
		return product;
	}

	public String save() {
		controller.updateProduct(product);
		return "/index?faces-redirect=true";
	}
}
