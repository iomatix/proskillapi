/**
 * SkillAPI
 * com.sucy.skill.dynamic.mechanic.ValueHealth
 * <p>
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Steven Sucy
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sucy.skill.dynamic.mechanic;

import com.sucy.skill.dynamic.DynamicSkill;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.List;

public class ValueHealthMechanic extends MechanicComponent {
    private static final String KEY  = "key";
    private static final String TYPE = "type";

    @Override
    public String getKey() {
        return "value health";
    }

    /**
     * Executes the component
     *
     * @param caster  caster of the skill
     * @param level   level of the skill
     * @param targets targets to apply to
     *
     * @param force
     * @return true if applied to something, false otherwise
     */
    @Override
    public boolean execute(LivingEntity caster, int level, List<LivingEntity> targets, boolean force) {
        final String                  key  = settings.getString(KEY);
        final String                  type = settings.getString(TYPE, "current").toLowerCase();
        final HashMap<String, Object> data = DynamicSkill.getCastData(caster);

        final LivingEntity target = targets.get(0);
        switch (type) {
            case "max":
                data.put(key, target.getMaxHealth());
                break;
            case "percent":
                data.put(key, target.getHealth() / target.getMaxHealth());
                break;
            case "missing":
                data.put(key, target.getMaxHealth() - target.getHealth());
                break;
            default: // current
                data.put(key, target.getHealth());
        }
        return true;
    }
}
