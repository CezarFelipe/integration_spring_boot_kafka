package br.app.kafka.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Shipping implements Serializable {

        private String identifier;
        private String customer;

        public String getIdentifier() {
                return identifier;
        }

        public void setIdentifier(String identifier) {
                this.identifier = identifier;
        }

        public String getCustomer() {
                return customer;
        }

        public void setCustomer(String customer) {
                this.customer = customer;
        }

        public BigDecimal getValue() {
                return value;
        }

        public void setValue(BigDecimal value) {
                this.value = value;
        }

        private BigDecimal value;
}
