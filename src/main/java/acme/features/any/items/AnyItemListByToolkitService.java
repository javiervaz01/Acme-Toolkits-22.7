package acme.features.any.items;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyItemListByToolkitService implements AbstractListService<Any,Item>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyItemRepository repository;

		@Override
		public boolean authorise(final Request<Item> request) {
			assert request != null;
			
			return true;
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


