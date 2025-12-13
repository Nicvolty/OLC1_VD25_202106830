/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Simbolo.Arbol;
import Simbolo.Tipo;
import Simbolo.tablaSimbolos;
import Simbolo.tipoDato;
import abstracto.Instruccion;
import excepciones.Errores;

/**
 *
 * @author manue
 */
public class Logicos extends Instruccion {
    private Instruccion condicion1;
    private Instruccion condicion2;
    private OperadoresLogicos opLogico;
    private Instruccion condUnica;

    public Logicos(Instruccion condicion1, Instruccion condicion2, OperadoresLogicos opLogico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.condicion1 = condicion1;
        this.condicion2 = condicion2;
        this.opLogico = opLogico;
    }

    public Logicos(OperadoresLogicos opLogico,Instruccion condUnica, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.opLogico = opLogico;
        this.condUnica = condUnica;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla){
    Object condicionUnica = null, cond1 = null, cond2= null;    
    if (this.condUnica != null){
       condicionUnica = this.condUnica.interpretar(arbol, tabla);
       if (condicionUnica instanceof Errores){
           return condicionUnica;
       }
    }
    else{
        cond1 = this.condicion1.interpretar(arbol,tabla);
        if(cond1 instanceof Errores){
        return cond1;
        }
        cond2 = this.condicion2.interpretar(arbol, tabla);
        if(cond2 instanceof Errores){
         return cond2;
        }
       }
    
    return switch(opLogico){
        case OR ->
           this.or(cond1,cond2);
        case AND ->
         this.and(cond1,cond2);
        case XOR ->
         this.xor(cond1,cond2);
        case NOT ->
         this.not(condicionUnica);
        default ->
          new Errores("ERROR semantico", "operando logico inexistente", this.linea, this.col);
    };
    
    }
    public Object or(Object c1,Object c2){
       return (boolean)c1 || (boolean)c2;
    }
    public Object and(Object c1, Object c2){
       return (boolean)c1 && (boolean)c2;
    }
    public Object xor(Object c1, Object c2){
      return (boolean)c1 ^ (boolean)c2;
    }
    public Object not(Object cu){
        return !(boolean)cu;
    }
}
