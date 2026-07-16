package com.gdeal.brewmaster.dto;

public class RecipeExportResult {
    
    private final String filename;
    private final byte[] content;

    public RecipeExportResult(

        String filename,
        byte[] content) {

            this.filename = filename;
            this.content = content;
        }

    public String getFilename() {

        return filename;
    }

    public byte[] getContent() {

        return content;
    }
}
