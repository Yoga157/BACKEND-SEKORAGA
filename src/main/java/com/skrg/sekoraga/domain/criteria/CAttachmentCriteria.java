package com.skrg.sekoraga.domain.criteria;

import java.io.Serializable;

import com.skrg.sekoraga.util.filter.Criteria;
import com.skrg.sekoraga.util.filter.LongFilter;
import com.skrg.sekoraga.util.filter.StringFilter;

public class CAttachmentCriteria implements Serializable, Criteria {
    private LongFilter id;
    private StringFilter fileName;
    private StringFilter mimeType;
    private StringFilter documentType;
    private StringFilter type;

    public CAttachmentCriteria() {
    }

    public CAttachmentCriteria(CAttachmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.mimeType = other.mimeType == null ? null : other.mimeType.copy();
        this.documentType = other.documentType == null ? null : other.documentType.copy();
        this.type = other.type == null ? null : other.type.copy();
    }

    @Override
    public CAttachmentCriteria copy() {
        return new CAttachmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFileName() {
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public StringFilter getMimeType() {
        return mimeType;
    }

    public void setMimeType(StringFilter mimeType) {
        this.mimeType = mimeType;
    }

    public StringFilter getDocumentType() {
        return documentType;
    }

    public void setDocumentType(StringFilter documentType) {
        this.documentType = documentType;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }
}
