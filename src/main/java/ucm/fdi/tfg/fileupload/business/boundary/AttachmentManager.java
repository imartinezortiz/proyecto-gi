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
package ucm.fdi.tfg.fileupload.business.boundary;

import java.io.IOException;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ucm.fdi.tfg.fileupload.business.control.AttachmentRepository;
import ucm.fdi.tfg.fileupload.business.entity.Attachment;
import ucm.fdi.tfg.storage.business.boundary.StorageManager;
import ucm.fdi.tfg.storage.business.entity.StorageObjectId;

@Service
@Transactional
public class AttachmentManager {

	private static final String ATTACHMENT_PREFIX = "attachment/";
	
	private StorageManager storageManager;

	private AttachmentRepository attachments;
	
	private String bucket;

	@Autowired
	public AttachmentManager(StorageManager storageManager, AttachmentRepository attachments, @Value("#{examplePrefs[bucket]}") String bucket) {
		this.storageManager = storageManager;
		this.attachments = attachments;
		this.bucket = bucket;
	}

	public Attachment addAttachment(NewFileCommand command) throws IOException {

		Attachment attachment = attachments.save(new Attachment(command.getDescription()));
		
		MultipartFile attachmentFile = command.getAttachment();
		String key = getStorageKey(attachment.getId());
		attachment.setStorageKey(key);
		String mimeType = attachmentFile.getContentType();
		storageManager.putObject(bucket, key, mimeType, attachmentFile.getInputStream());			
		attachment.setAttachmentUrl(storageManager.getUrl(bucket, key));
		attachments.save(attachment);
		return attachment;
	}
	
	private String getStorageKey(Long id) {
		return ATTACHMENT_PREFIX+Long.toString(id);
	}

	public Collection<Attachment> getAttachments() {
		return attachments.findAll();
	}

	public void deleteAttachment(long id) {
		Attachment attachment = attachments.findOne(id);
		StorageObjectId storageId = new StorageObjectId(bucket, attachment.getStorageKey());
		try {
			storageManager.removeObject(storageId);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		attachments.delete(attachment);
	}

}