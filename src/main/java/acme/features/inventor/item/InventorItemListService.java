package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListService implements AbstractListService<Inventor,Item>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorItemRepository repository;

		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;
			
			int id;

			id = request.getModel().getInteger("masterId");
			
			final int inventorId = request.getPrincipal().getActiveRoleId();
			
			final Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(inventorId);
			
			final Toolkit requested = this.repository.findOneToolkitById(id);
			
			return toolkits.contains(requested);
		}
		
		@Override
		public Collection<Item> findMany(final Request<Item> request) {
			assert request != null;

			Collection<Item> result;
			final int id;

			id = request.getModel().getInteger("masterId");
			result = this.repository.findItemsByToolkitId(id);

			return result;
		}

		

		@Override
		public void unbind(final Request<Item> request, final Item entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			
			
			request.unbind(entity, model, "name","code","technology","description","retailPrice","info","type");
		}
}
