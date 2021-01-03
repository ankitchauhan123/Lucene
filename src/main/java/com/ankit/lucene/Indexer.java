package com.ankit.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

/***
 * @author: ankit chauhan
 * @date :  04-Dec-20
 ***/
public class Indexer {
    private IndexWriter indexWriter;
    private String sourceFolder;
    private String indexFolder;


    public Indexer(String indexFolder, String sourceFolder) throws IOException {
        this.indexFolder = indexFolder;
        this.sourceFolder = sourceFolder;
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(this.indexFolder));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
        indexWriter = new IndexWriter(directory, indexWriterConfig);
    }

    public void close() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
    }

    public int index() throws IOException {
        File[] files = new File(sourceFolder).listFiles();
        for (File file : files) {
            Document doc = getDocument(file);
            indexWriter.addDocument(doc);
        }
        System.out.println("Total Files Written in Index:" + files.length);
        return files.length;

    }

    public Document getDocument(File f) throws IOException {

        Document doc = new Document();
        FieldType contentFieldType = new FieldType();
        contentFieldType.setIndexOptions(IndexOptions.DOCS);
        contentFieldType.setStored(true);
        doc.add(new Field("contents", getFileContents(f), contentFieldType));

        FieldType filenameFieldType = new FieldType();
        filenameFieldType.setIndexOptions(IndexOptions.DOCS);
        filenameFieldType.setStored(true);
        doc.add(new Field("filename", f.getName(), filenameFieldType));

        FieldType canonicalPathFieldType = new FieldType();
        canonicalPathFieldType.setStored(true);
        canonicalPathFieldType.setIndexOptions(IndexOptions.DOCS);
        doc.add(new Field("fullpath", f.getName(), canonicalPathFieldType));
        return doc;

    }

    public String getFileContents(File f) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String finalText = null;
        String line = null;
        while ((line = reader.readLine()) != null) {
            finalText = finalText + "\n" + line;
        }
        return finalText;
    }
}
