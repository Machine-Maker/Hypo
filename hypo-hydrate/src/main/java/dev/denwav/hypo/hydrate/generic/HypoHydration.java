/*
 * Hypo, an extensible and pluggable Java bytecode analytical model.
 *
 * Copyright (C) 2023  Kyle Wood (DenWav)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License only.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.denwav.hypo.hydrate.generic;

import dev.denwav.hypo.model.data.ClassData;
import dev.denwav.hypo.model.data.HypoKey;
import dev.denwav.hypo.model.data.MethodData;
import java.util.List;

/**
 * Core common data keys for hydration. Hydrators for these keys are implemented in the {@code hypo-asm} module.
 */
public final class HypoHydration {

    private HypoHydration() {}

    /**
     * The {@link MethodData method} the synthetic method this {@link HypoKey} is set on targets.
     */
    public static final HypoKey<MethodData> SYNTHETIC_TARGET = HypoKey.create("Synthetic Target");
    /**
     * The synthetic {@link MethodData method} which calls the method this {@link HypoKey} is set on.
     */
    public static final HypoKey<MethodData> SYNTHETIC_SOURCE = HypoKey.create("Synthetic Source");

    /**
     * The list of {@link SuperCall super calls} which calls the constructor this {@link HypoKey} is set on.
     */
    public static final HypoKey<List<SuperCall>> SUPER_CALLER_SOURCES = HypoKey.create("Constructor Super Call Sources");
    /**
     * The {@link SuperCall super call} the constructor this {@link HypoKey} is set on calls.
     */
    public static final HypoKey<SuperCall> SUPER_CALL_TARGET = HypoKey.create("Constructor Super Call Target");

    /**
     * The list of {@link ClassData classes} representing local and anonymous classes present in the method this
     * {@link HypoKey} is set on.
     *
     * <p>This key is symmetric, it is set for both the containing and the local / anonymous class.
     */
    public static final HypoKey<List<LocalClassClosure>> LOCAL_CLASSES = HypoKey.create("Local Classes");

    /**
     * The list of {@link MethodData methods} representing lambdas present in the method this {@link HypoKey} is set
     * on. These are the synthetic methods which are generated as the implementation of the lambda expression.
     *
     * <p>This key is symmetric, it is set for both the containing and lambda methods.
     */
    public static final HypoKey<List<LambdaClosure>> LAMBDA_CALLS = HypoKey.create("Lambda Calls");
}
