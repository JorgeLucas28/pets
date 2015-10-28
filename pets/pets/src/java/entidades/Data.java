/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jodi
 */
public class Data {

    private Date data;
    private DateFormat format;
    private DateFormat formatCompeto;
    private String dataFormatada;
    private String dataCompletaFormatada;

    public Data() {
        this.data = new Date();
        this.format = DateFormat.getDateTimeInstance();
        this.dataFormatada = format.format(this.data);
        this.formatCompeto = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("PT", "br"));
        this.dataCompletaFormatada = this.formatCompeto.format(data);
        this.dataCompletaFormatada = this.dataCompletaFormatada.replace("GMT-03:00", "");
    }

    public Data(long tempo) {
        this.data = new Date(tempo);
        this.format = DateFormat.getDateTimeInstance();
        this.dataFormatada = format.format(this.data);
        this.formatCompeto = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("PT", "br"));
        this.dataCompletaFormatada = this.formatCompeto.format(data);
    }

    public long getTempo() {
        return this.data.getTime();
    }

    public void setData(long tempo) {
        this.data = new Date(tempo);
        this.format = DateFormat.getDateTimeInstance();
        this.dataFormatada = format.format(this.data);
        this.format = DateFormat.getDateTimeInstance();
        this.dataFormatada = format.format(this.data);
        this.formatCompeto = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("PT", "br"));
        this.dataCompletaFormatada = this.formatCompeto.format(data);
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public String getDataCompletaFormatada() {
        return dataCompletaFormatada;
    }

}
