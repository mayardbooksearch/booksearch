package com.mayard.booksearch.book.model.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ApiResponseVo {

    private ApiResponseMetaVo meta;
    private List<ApiResponseDocumentVo> documents;

    public ApiResponseMetaVo getMeta() {
        return meta;
    }

    public void setMeta(ApiResponseMetaVo meta) {
        this.meta = meta;
    }

    public List<ApiResponseDocumentVo> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ApiResponseDocumentVo> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
