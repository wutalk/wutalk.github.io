/*
 * @(#)	2016年8月2日
 * Copyright (c) 2016 @wutalk on github. All rights reserved.
 */
package com.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

/**
 * 
 * @author wutalk
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Main app = new Main();
         app.f();
        app.fromJson();
    }

    void f() {
        Condition condition = new Condition(OP.EQUAL, "status", "active");
        System.out.println(condition.evaluate());
        Condition condition2 = new Condition(OP.GREAT_THAN, "alarmNumber", "20005");
        Condition condition3 = new Condition(OP.NOT_EQUAL, "severity", "warning");
        Condition condition4 = new Condition(OP.BETWEEN, "age", "10", "20");

        Gson gson = new Gson();
        String json = gson.toJson(condition);
        System.out.println(json);

        Composite comp1 = new Composite(MatchOP.AND, new Condition[] { condition, condition2 },
                null);
        Composite comp2 = new Composite(MatchOP.OR, new Condition[] { condition3, condition4 },
                new Composite[] { comp1 });

        System.out.println(comp1.evaluate());
        System.out.println(gson.toJson(comp1));
        System.out.println(gson.toJson(comp2));

    }

    void fromJson() {
        Gson gson = new Gson();
        String jsonStr = "";
        try {
            jsonStr = FileUtils.readFileToString(
                    new File("D:\\sourcecode\\wutalk.github.io\\javaByExamples\\json.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Composite fromJson = gson.fromJson(jsonStr, Composite.class);
        System.out.println(fromJson.evaluate());
    }

    class Composite {
        MatchOP comp_op;
        Condition[] conditions;
        Composite[] composites;

        public Composite(MatchOP operator, Condition[] conditions, Composite[] composites) {
            this.comp_op = operator;
            this.conditions = conditions;
            this.composites = composites;
        }

        public String evaluate() {
            return comp_op.evaluate(conditions, composites);
        }
    }

    class Condition {
        OP operator;
        String property;
        String value;
        String to;

        public Condition(OP operator, String property, String value) {
            this.operator = operator;
            this.property = property;
            this.value = value;
        }

        public Condition(OP operator, String property, String value, String to) {
            this(operator, property, value);
            this.to = to;
        }

        public String evaluate() {
            if (to == null) {
                return operator.evaluate(property, value);
            } else {
                return operator.evaluate(property, value, to);
            }
        }
    }

    public enum MatchOP {
        AND {
            @Override
            public String evaluate(Condition[] conditions, Composite[] composites) {
                return gen(conditions, composites, " AND ");
            }
        },
        OR {
            @Override
            public String evaluate(Condition[] conditions, Composite[] composites) {
                return gen(conditions, composites, " OR ");
            }
        };
        public String evaluate(Condition[] conditions, Composite[] composites) {
            return null;
        }

        public String gen(Condition[] conditions, Composite[] composites, String OP_AND) {
            StringBuilder result = new StringBuilder();
            if (conditions == null || conditions.length == 0) {
                // ! DO NOTHING
            } else if (conditions.length == 1) {
                result.append(conditions[0].evaluate());
                result.append(OP_AND);
            } else {
                for (Condition c : conditions) {
                    result.append(c.evaluate());
                    result.append(OP_AND);
                }
            }
            if (composites == null || composites.length == 0) {
                // ! DO NOTHING
            } else {
                for (Composite c : composites) {
                    result.append("(");
                    result.append(c.evaluate());
                    result.append(")");
                    result.append(OP_AND);
                }
            }
            if (result.toString().endsWith(OP_AND)) {
                result.delete(result.length() - OP_AND.length(), result.length());
            }
            return result.toString();
        }
    }

    /**
     * Operator evaluates to SQL result
     * 
     * @author owu
     */
    public enum OP {
        EQUAL {
            @Override
            public String evaluate(String property, String value) {
                return String.format("%s='%s'", property, value);
            }
        },
        NOT_EQUAL {
            @Override
            public String evaluate(String property, String value) {
                return String.format("%s!='%s'", property, value);
            }
        },
        GREAT_THAN {
            @Override
            public String evaluate(String property, String value) {
                return String.format("%s>%s", property, value);
            }
        },
        LESS_THAN {
            @Override
            public String evaluate(String property, String value) {
                return String.format("%s<%s", property, value);
            }
        },
        BETWEEN {
            @Override
            public String evaluate(String property, String from, String to) {
                return String.format("(%s BETWEEN %s AND %s)", property, from, to);
            }
        };

        public String evaluate(String property, String value) {
            return null;
        }

        public String evaluate(String property, String from, String to) {
            return null;
        }
    }

    public class Operand {

        private String key;

        public Operand(String val) {
            this.key = val;
        }

        public Object value() {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
