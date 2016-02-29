/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.DoubleList;
import org.eclipse.collections.impl.block.factory.primitive.DoublePredicates;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

public class Exercise5Test extends CompanyDomainForKata
{
    @Test
    public void filterOrderValues()
    {
        /**
         * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
         * instead.
         * Get the order values that are greater than 1.5.
         */
        DoubleList filtered = this.company.getMostRecentCustomer()
                .getOrders()
                .asLazy()
                .collectDouble(Order::getValue)
                .select(DoublePredicates.greaterThan(1.5))
                .toSortedList();
        Assert.assertEquals(DoubleLists.immutable.with(1.75, 372.5), filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }

    @Test
    public void filterOrders()
    {
        MutableList<Order> orders = this.company.getMostRecentCustomer().getOrders();
        /**
         * Same exercise but don't use static utility - refactor the type of orders and {@link Customer#getOrders()}
         * instead.
         * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
         */
        MutableList<Order> filtered = orders.select(order -> order.getValue() > 2.0);
        Assert.assertEquals(FastList.newListWith(this.company.getMostRecentCustomer().getOrders().getFirst()), filtered);
        Verify.assertInstanceOf(MutableList.class, this.company.getMostRecentCustomer().getOrders());
        this.company.getMostRecentCustomer().getOrders().add(null);
        Verify.assertContains("Don't return a copy from Customer.getOrders(). The field should be a MutableList.", null, this.company.getMostRecentCustomer().getOrders());
    }
}
