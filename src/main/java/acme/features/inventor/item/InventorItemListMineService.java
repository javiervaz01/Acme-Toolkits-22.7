package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListMineService implements AbstractListService<Inventor, Item> {
	
	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected ExchangeService exchangeRepository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		

		Collection<Item> result;
		final Integer id = request.getPrincipal().getActiveRoleId();
		result = this.repository.findItemsByInventorId(id);
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type");
		
		final Integer id = entity.getId();
		final Quantity quantity = this.repository.findQuantityByItemId(id);
		if (quantity == null) return;
		model.setAttribute("quantity", quantity.getNumber());
		
		final Money exchange=this.exchangeRepository.getExchange(entity.getRetailPrice());
		model.setAttribute("exchange", exchange);
	}
}
