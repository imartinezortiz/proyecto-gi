/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
package ucm.fdi.tfg.events;

import java.util.ArrayList;
import java.util.List;

public class DomainEvent<E> {

	private ThreadLocal<List<Action1<E>>> actions = new ThreadLocal<List<Action1<E>>>(){
        @Override
        protected List<Action1<E>> initialValue() {
            return new ArrayList<>();
        }
	}; 
	
	private List<Action1<E>> getActions() {
		return actions.get();
	}

	public DomainEventRegistrationRemover register(Action1<E> callback) {
		getActions().add(callback);
		return new DomainEventRegistrationRemover(new DomainEventRegistrationRemoverAction(callback)); 
	}

	public void notify(E args) {
		List<Action1<E>> actions = getActions();
		for (Action1<E> action : actions) { 
			action.execute(args);
		}
	}
	
	private class DomainEventRegistrationRemoverAction implements Action0 {
		
		private final Action1<E> listener;

		public DomainEventRegistrationRemoverAction(Action1<E> listener) {
			this.listener = listener; 
		}
		@Override
		public void execute() {
			getActions().remove(listener);
		}

	}
}