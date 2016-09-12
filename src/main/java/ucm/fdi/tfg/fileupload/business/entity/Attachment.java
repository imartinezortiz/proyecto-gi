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
package ucm.fdi.tfg.fileupload.business.entity;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Attachment {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;

	private URL attachmentUrl;
	
	private String storageKey;
	
	Attachment ( ){
		
	}
	
	public Attachment(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URL getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(URL attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getStorageKey() {
		return storageKey;
	}

	public void setStorageKey(String storageKey) {
		this.storageKey = storageKey;
	}

}
