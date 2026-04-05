/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package visible.org.codehaus.groovy.ast;

import visible.org.codehaus.groovy.ast.expr.ArgumentListExpression;
import visible.org.codehaus.groovy.ast.expr.ArrayExpression;
import visible.org.codehaus.groovy.ast.expr.AttributeExpression;
import visible.org.codehaus.groovy.ast.expr.BinaryExpression;
import visible.org.codehaus.groovy.ast.expr.BitwiseNegationExpression;
import visible.org.codehaus.groovy.ast.expr.BooleanExpression;
import visible.org.codehaus.groovy.ast.expr.CastExpression;
import visible.org.codehaus.groovy.ast.expr.ClassExpression;
import visible.org.codehaus.groovy.ast.expr.ClosureExpression;
import visible.org.codehaus.groovy.ast.expr.ClosureListExpression;
import visible.org.codehaus.groovy.ast.expr.ConstantExpression;
import visible.org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import visible.org.codehaus.groovy.ast.expr.DeclarationExpression;
import visible.org.codehaus.groovy.ast.expr.ElvisOperatorExpression;
import visible.org.codehaus.groovy.ast.expr.EmptyExpression;
import visible.org.codehaus.groovy.ast.expr.Expression;
import visible.org.codehaus.groovy.ast.expr.FieldExpression;
import visible.org.codehaus.groovy.ast.expr.GStringExpression;
import visible.org.codehaus.groovy.ast.expr.LambdaExpression;
import visible.org.codehaus.groovy.ast.expr.ListExpression;
import visible.org.codehaus.groovy.ast.expr.MapEntryExpression;
import visible.org.codehaus.groovy.ast.expr.MapExpression;
import visible.org.codehaus.groovy.ast.expr.MethodCallExpression;
import visible.org.codehaus.groovy.ast.expr.MethodPointerExpression;
import visible.org.codehaus.groovy.ast.expr.MethodReferenceExpression;
import visible.org.codehaus.groovy.ast.expr.NotExpression;
import visible.org.codehaus.groovy.ast.expr.PostfixExpression;
import visible.org.codehaus.groovy.ast.expr.PrefixExpression;
import visible.org.codehaus.groovy.ast.expr.PropertyExpression;
import visible.org.codehaus.groovy.ast.expr.RangeExpression;
import visible.org.codehaus.groovy.ast.expr.SpreadExpression;
import visible.org.codehaus.groovy.ast.expr.SpreadMapExpression;
import visible.org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import visible.org.codehaus.groovy.ast.expr.TernaryExpression;
import visible.org.codehaus.groovy.ast.expr.TupleExpression;
import visible.org.codehaus.groovy.ast.expr.UnaryMinusExpression;
import visible.org.codehaus.groovy.ast.expr.UnaryPlusExpression;
import visible.org.codehaus.groovy.ast.expr.VariableExpression;
import visible.org.codehaus.groovy.ast.stmt.AssertStatement;
import visible.org.codehaus.groovy.ast.stmt.BlockStatement;
import visible.org.codehaus.groovy.ast.stmt.BreakStatement;
import visible.org.codehaus.groovy.ast.stmt.CaseStatement;
import visible.org.codehaus.groovy.ast.stmt.CatchStatement;
import visible.org.codehaus.groovy.ast.stmt.ContinueStatement;
import visible.org.codehaus.groovy.ast.stmt.DoWhileStatement;
import visible.org.codehaus.groovy.ast.stmt.EmptyStatement;
import visible.org.codehaus.groovy.ast.stmt.ExpressionStatement;
import visible.org.codehaus.groovy.ast.stmt.ForStatement;
import visible.org.codehaus.groovy.ast.stmt.IfStatement;
import visible.org.codehaus.groovy.ast.stmt.ReturnStatement;
import visible.org.codehaus.groovy.ast.stmt.Statement;
import visible.org.codehaus.groovy.ast.stmt.SwitchStatement;
import visible.org.codehaus.groovy.ast.stmt.SynchronizedStatement;
import visible.org.codehaus.groovy.ast.stmt.ThrowStatement;
import visible.org.codehaus.groovy.ast.stmt.TryCatchStatement;
import visible.org.codehaus.groovy.ast.stmt.WhileStatement;
import visible.org.codehaus.groovy.classgen.BytecodeExpression;

import java.util.List;

/**
 * An implementation of the visitor pattern for working with ASTNodes.
 */
public interface GroovyCodeVisitor {

    //--------------------------------------------------------------------------
    // statements

    void visitBlockStatement(BlockStatement statement);

    void visitForLoop(ForStatement statement);

    void visitWhileLoop(WhileStatement statement);

    void visitDoWhileLoop(DoWhileStatement statement);

    void visitIfElse(IfStatement statement);

    void visitExpressionStatement(ExpressionStatement statement);

    void visitReturnStatement(ReturnStatement statement);

    void visitAssertStatement(AssertStatement statement);

    void visitTryCatchFinally(TryCatchStatement statement);

    void visitSwitch(SwitchStatement statement);

    void visitCaseStatement(CaseStatement statement);

    void visitBreakStatement(BreakStatement statement);

    void visitContinueStatement(ContinueStatement statement);

    void visitThrowStatement(ThrowStatement statement);

    void visitSynchronizedStatement(SynchronizedStatement statement);

    void visitCatchStatement(CatchStatement statement);

    default void visitEmptyStatement(EmptyStatement statement) {
    }

    default void visit(final Statement statement) {
        if (statement != null) {
            statement.visit(this);
        }
    }

    //--------------------------------------------------------------------------
    // expressions

    void visitMethodCallExpression(MethodCallExpression expression);

    void visitStaticMethodCallExpression(StaticMethodCallExpression expression);

    void visitConstructorCallExpression(ConstructorCallExpression expression);

    void visitTernaryExpression(TernaryExpression expression);

    void visitShortTernaryExpression(ElvisOperatorExpression expression);

    void visitBinaryExpression(BinaryExpression expression);

    void visitPrefixExpression(PrefixExpression expression);

    void visitPostfixExpression(PostfixExpression expression);

    void visitBooleanExpression(BooleanExpression expression);

    void visitClosureExpression(ClosureExpression expression);

    void visitLambdaExpression(LambdaExpression expression);

    void visitTupleExpression(TupleExpression expression);

    void visitMapExpression(MapExpression expression);

    void visitMapEntryExpression(MapEntryExpression expression);

    void visitListExpression(ListExpression expression);

    void visitRangeExpression(RangeExpression expression);

    void visitPropertyExpression(PropertyExpression expression);

    void visitAttributeExpression(AttributeExpression expression);

    void visitFieldExpression(FieldExpression expression);

    void visitMethodPointerExpression(MethodPointerExpression expression);

    void visitMethodReferenceExpression(MethodReferenceExpression expression);

    void visitConstantExpression(ConstantExpression expression);

    void visitClassExpression(ClassExpression expression);

    void visitVariableExpression(VariableExpression expression);

    void visitDeclarationExpression(DeclarationExpression expression);

    void visitGStringExpression(GStringExpression expression);

    void visitArrayExpression(ArrayExpression expression);

    void visitSpreadExpression(SpreadExpression expression);

    void visitSpreadMapExpression(SpreadMapExpression expression);

    void visitNotExpression(NotExpression expression);

    void visitUnaryMinusExpression(UnaryMinusExpression expression);

    void visitUnaryPlusExpression(UnaryPlusExpression expression);

    void visitBitwiseNegationExpression(BitwiseNegationExpression expression);

    void visitCastExpression(CastExpression expression);

    void visitArgumentlistExpression(ArgumentListExpression expression);

    void visitClosureListExpression(ClosureListExpression expression);

    void visitBytecodeExpression(BytecodeExpression expression);

    default void visitEmptyExpression(EmptyExpression expression) {
    }

    default void visitListOfExpressions(final List<? extends Expression> list) {
        if (list != null) {
            for (Expression expr: list) {
                expr.visit(this);
            }
        }
    }

    default void visit(final Expression expression) {
        if (expression != null) {
            expression.visit(this);
        }
    }
}
