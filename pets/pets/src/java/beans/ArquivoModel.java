/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;


public class ArquivoModel {

    private Part arquivo;
    private String caminho = "D:\\git\\pets\\pets\\pets\\web\\uploadimagem\\";

    public ArquivoModel() {
        this.arquivo = null;
    }

    public String camihoArquivo() {
        
        return (caminho + System.nanoTime() + this.getArquivo().getSubmittedFileName().substring(this.getArquivo().getSubmittedFileName().lastIndexOf("."), this.getArquivo().getSubmittedFileName().length()));

    }

    public void salvar() {
        
        try {
            try (InputStream inputStream = this.arquivo.getInputStream(); 
                    FileOutputStream outputStream = new FileOutputStream(this.camihoArquivo())) {
                
                byte[] buffer = new byte[10240];
                int bytesRead = 0;
                while (true) {
                    bytesRead = inputStream.read(buffer);
                    if (bytesRead > 0) {
                        outputStream.write(buffer, 0, bytesRead);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the arquivo
     */
    public Part getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

}
