package com.dong.sitserver.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


public class DAOException extends RuntimeException {

    private static final long serialVersionUID = -7424622277723376766L;

    //定义保存异常信息
    private Throwable cause;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
    }

    public DAOException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    /**
     * 重写 getCause() 方法
     *
     * @return Throwable 对象
     */
    @Override
    public Throwable getCause() {
        return null == this.cause ? null : this.cause;
    }

    /**
     * 重写父类的  getMessage()方法
     *
     * @return String
     */
    @Override
    public String getMessage() {
        StringBuffer message = new StringBuffer(super.getMessage());
        if (cause != null) {
            message.append("; DAO Exception is ").append(cause);
        }
        return message.toString();
    }

    /**
     * 重写父类的  printStackTrace 方法
     */
    @Override

    public void printStackTrace(PrintStream printStream) {
        if (null == getCause()) {
            super.printStackTrace(printStream);
        } else {
            printStream.println(this);
            getCause().printStackTrace(printStream);
        }
    }

    /**
     * 重写父类的  printStackTrace 方法
     */
    @Override

    public void printStackTrace(PrintWriter printWriter) {
        if (null == getCause()) {
            super.printStackTrace(printWriter);
        } else {
            printWriter.println(this);
            getCause().printStackTrace(printWriter);
        }
    }
}
