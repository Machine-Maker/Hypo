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

package dev.denwav.hypo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * {@link HypoModelUtilHelper} implementation for JDK 8 and 9.
 */
@SuppressWarnings("unused") // Loaded dynamically
class HypoModelUtilHelperJdk8 extends HypoModelUtilHelper {

    @Override
    @NotNull <T> List<T> asImmutableList(final @NotNull Collection<T> list) {
        return Collections.unmodifiableList(new ArrayList<>(list));
    }

    @SafeVarargs
    @Override
    final @NotNull <T> List<T> immutableListOf(final @NotNull T @NotNull ... array) {
        if (array.length == 0) {
            return Collections.emptyList();
        } else if (array.length == 1) {
            return Collections.singletonList(array[0]);
        } else {
            return Collections.unmodifiableList(Arrays.asList(array));
        }
    }
}
